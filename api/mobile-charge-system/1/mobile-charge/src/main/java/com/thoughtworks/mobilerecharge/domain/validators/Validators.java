package com.thoughtworks.mobilerecharge.domain.validators;


import com.thoughtworks.mobilerecharge.domain.Uniqueness;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Validators {
    public static void validate(Validator validator, Map<String, Object> info) {
        Optional<String> message = validator.validate(info);
        if (message.isPresent()) {
            throw new BadRequestException(String.join("\n", message.get()));
        }
    }

    public static Validator fieldNotEmpty(String name, String message) {
        return info -> info.getOrDefault(name, "").toString().trim().isEmpty() ? Optional.of(message) : Optional.empty();
    }

    public static <Key, Entity> Validator unique(String field, String message, Uniqueness<Key, Entity> range) {
        return info -> range.findBy((Key)info.getOrDefault(field, "")).map(e -> message);
    }

    public static Validator all(final Validator... validators) {
        return (info) -> {
            List<String> messages = asList(validators).stream().map(validator -> validator.validate(info))
                    .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
            return messages.size() != 0 ? Optional.of(String.join("\n", messages)) : Optional.empty();
        };
    }
}
