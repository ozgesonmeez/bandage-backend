package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.entity.AppUser;
import com.ozgesonmez.bandagebackend.entity.Card;
import com.ozgesonmez.bandagebackend.repository.CardRepository;
import com.ozgesonmez.bandagebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardService(
            CardRepository cardRepository,
            UserRepository userRepository
    ) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public List<Card> findAllByUserEmail(String email) {
        AppUser user = findUserByEmail(email);

        return cardRepository.findAllByUserIdOrderByIdDesc(user.getId());
    }

    @Transactional
    public Card createCard(String email, Card cardData) {
        AppUser user = findUserByEmail(email);

        Card card = new Card();
        card.setCardNo(cardData.getCardNo());
        card.setExpireMonth(cardData.getExpireMonth());
        card.setExpireYear(cardData.getExpireYear());
        card.setNameOnCard(cardData.getNameOnCard());
        card.setUser(user);

        return cardRepository.save(card);
    }

    @Transactional
    public Card updateCard(String email, Card cardData) {
        if (cardData.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Card id is required."
            );
        }

        AppUser user = findUserByEmail(email);

        Card existingCard = cardRepository
                .findByIdAndUserId(cardData.getId(), user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Card not found."
                ));

        existingCard.setCardNo(cardData.getCardNo());
        existingCard.setExpireMonth(cardData.getExpireMonth());
        existingCard.setExpireYear(cardData.getExpireYear());
        existingCard.setNameOnCard(cardData.getNameOnCard());

        return cardRepository.save(existingCard);
    }

    @Transactional
    public void deleteCard(String email, Long cardId) {
        AppUser user = findUserByEmail(email);

        Card card = cardRepository
                .findByIdAndUserId(cardId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Card not found."
                ));

        cardRepository.delete(card);
    }

    private AppUser findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "User not found."
                ));
    }
}