package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getPlayers(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "race", required = false) Race race,
                                   @RequestParam(value = "profession", required = false) Profession profession,
                                   @RequestParam(value = "after", required = false) Long after,
                                   @RequestParam(value = "before", required = false) Long before,
                                   @RequestParam(value = "banned", required = false) Boolean banned,
                                   @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                   @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                   @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                   @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                   @RequestParam(defaultValue = "ID", value = "order") PlayerOrder order,
                                   @RequestParam(defaultValue = "0", value = "pageNumber") Integer pageNumber,
                                   @RequestParam(defaultValue = "3", value = "pageSize") Integer pageSize) {

        Specification<Player> filter = playerService.setFilterPlayerSpec(name,title,race,profession,after,before,banned,
                                                                        minExperience,maxExperience,minLevel,maxLevel);

        Page results = playerService.getAll(filter, PageRequest.of(pageNumber,pageSize, Sort.by(order.getFieldName())));
        List<Player> resultList = results.getContent();

        return resultList;
    }
    @GetMapping("/players/count")
    public Long getPlayersCount(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "race", required = false) Race race,
                                   @RequestParam(value = "profession", required = false) Profession profession,
                                   @RequestParam(value = "after", required = false) Long after,
                                   @RequestParam(value = "before", required = false) Long before,
                                   @RequestParam(value = "banned", required = false) Boolean banned,
                                   @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                   @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                   @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                   @RequestParam(value = "maxLevel", required = false) Integer maxLevel) {

        Specification<Player> filter = playerService.setFilterPlayerSpec(name,title,race,profession,after,before,banned,
                                                                        minExperience,maxExperience, minLevel,maxLevel);

        Page results = playerService.getAll(filter, PageRequest.of(0,3, Sort.by("name")));

        return results.getTotalElements();
    }
    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getById(id);
    }
    @DeleteMapping("/players/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        return playerService.delete(id);
    }
    @PostMapping("/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id,
                               @RequestBody Player player) {
        return playerService.update(id,player);
    }
    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.add(player);
    }
}