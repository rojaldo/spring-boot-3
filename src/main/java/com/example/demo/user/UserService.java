package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.trivial.TrivialCardEntity;
import com.example.demo.trivial.TrivialRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrivialRepository trivialCardRepository;

    public Iterable<UserDto> getUsers() {
        Iterable<UserEntity> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity user : users) {
            UserDto userDto = UserDto.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .cardsId(user.getCards().stream().map(TrivialCardEntity::getId).toArray(Long[]::new))
                    .build();
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Transactional
    public UserDto createUser(UserDto user) {
        List<TrivialCardEntity> cards = new ArrayList<>();
        for (long cardID : user.getCardsId()) {
            Optional<TrivialCardEntity> card = this.trivialCardRepository.findById(cardID);
            if (card.isPresent()) {
                cards.add(card.get());
            }

        }
        UserEntity userEntity = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .cards(cards)
                .build();
        this.userRepository.save(userEntity);
        return user;
    }

    @Transactional
    public UserDto updateUser(UserDto user, Long id) {
        log.info("ID: " + id.toString());
        UserEntity userEntity = this.userRepository.findById(id).get();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        this.userRepository.save(userEntity);
        return user;
    }

    @Transactional
    public UserDto deleteUser(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return UserDto.builder()
                    .name(user.get().getName())
                    .email(user.get().getEmail())
                    .build();
        }else {
            return null;
        }
    }

}
