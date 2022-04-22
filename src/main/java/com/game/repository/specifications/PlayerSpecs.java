package com.game.repository.specifications;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class PlayerSpecs {
    public static Specification<Player> nameContains(String word) {
        return (Specification<Player>) (root,criteriaQuery,criteriaBuilder)
                -> criteriaBuilder.like(root.get("name"),"%" + word + "%");
    }
    public static Specification<Player> titleContains(String word) {
        return (Specification<Player>) (root,criteriaQuery,criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"),"%" + word + "%");
    }
    public static Specification<Player> raceContains(Race race) {
        return (Specification<Player>) (root,criteriaQuery,criteriaBuilder)
                -> criteriaBuilder.equal(root.get("race"),race);
    }
    public static Specification<Player> professionContains(Profession profession) {
        return (Specification<Player>) (root,criteriaQuery,criteriaBuilder)
                -> criteriaBuilder.equal(root.get("profession"),profession);
    }
    public static Specification<Player> equalBanned(Boolean banned) {
        return (Specification<Player>) (root,criteriaQuery,criteriaBuilder)
                -> criteriaBuilder.equal(root.get("banned"),banned);
    }
    public static Specification<Player> birthdayGreaterThanOrEq(Long after) {
        final Date afterDate = after == null ? null : new Date(after);
        return (Specification<Player>) (root,criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"),afterDate);
    }
    public static Specification<Player> birthdayLessThanOrEq(Long before) {
        final Date beforeDate = before == null ? null : new Date(before);
        return (Specification<Player>) (root,criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("birthday"),beforeDate);
    }
    public static Specification<Player> experienceGreaterThanOrEq(Integer minExperience) {
        return (Specification<Player>) (root,criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("experience"),minExperience);
    }
    public static Specification<Player> experienceLessThanOrEq(Integer maxExperience) {
        return (Specification<Player>) (root,criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("experience"),maxExperience);
    }
    public static Specification<Player> levelGreaterThanOrEq(Integer minLevel) {
        return (Specification<Player>) (root,criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("level"),minLevel);
    }
    public static Specification<Player> levelLessThanOrEq(Integer maxLevel) {
        return (Specification<Player>) (root,criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("level"),maxLevel);
    }
}
