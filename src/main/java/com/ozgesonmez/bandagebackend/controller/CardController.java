package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.entity.Card;
import com.ozgesonmez.bandagebackend.service.CardService;
import com.ozgesonmez.bandagebackend.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user/card")
@CrossOrigin
public class CardController {

    private final CardService cardService;
    private final JwtService jwtService;

    public CardController(
            CardService cardService,
            JwtService jwtService
    ) {
        this.cardService = cardService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<CardResponse> getCards(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String email = extractEmail(authorizationHeader);

        return cardService.findAllByUserEmail(email)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse createCard(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CardRequest request
    ) {
        String email = extractEmail(authorizationHeader);

        Card cardData = toEntity(request);
        Card savedCard = cardService.createCard(email, cardData);

        return toResponse(savedCard);
    }

    @PutMapping
    public CardResponse updateCard(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CardRequest request
    ) {
        String email = extractEmail(authorizationHeader);

        if (request.id() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Card id is required."
            );
        }

        Card cardData = toEntity(request);
        cardData.setId(request.id());

        Card updatedCard = cardService.updateCard(email, cardData);

        return toResponse(updatedCard);
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long cardId
    ) {
        String email = extractEmail(authorizationHeader);

        cardService.deleteCard(email, cardId);
    }

    private String extractEmail(String authorizationHeader) {
        if (
                authorizationHeader == null ||
                        !authorizationHeader.startsWith("Bearer ")
        ) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authorization token is missing."
            );
        }

        String token = authorizationHeader.substring(7);

        try {
            if (!jwtService.isTokenValid(token)) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Token is invalid or expired."
                );
            }

            return jwtService.extractEmail(token);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Token is invalid or expired."
            );
        }
    }

    private Card toEntity(CardRequest request) {
        Card card = new Card();

        card.setCardNo(request.card_no());
        card.setExpireMonth(request.expire_month());
        card.setExpireYear(request.expire_year());
        card.setNameOnCard(request.name_on_card());

        return card;
    }

    private CardResponse toResponse(Card card) {
        return new CardResponse(
                card.getId(),
                card.getCardNo(),
                card.getExpireMonth(),
                card.getExpireYear(),
                card.getNameOnCard()
        );
    }

    public record CardRequest(
            Long id,
            String card_no,
            Integer expire_month,
            Integer expire_year,
            String name_on_card
    ) {
    }

    public record CardResponse(
            Long id,
            String card_no,
            Integer expire_month,
            Integer expire_year,
            String name_on_card
    ) {
    }
}