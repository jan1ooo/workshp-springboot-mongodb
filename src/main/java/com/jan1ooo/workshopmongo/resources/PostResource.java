package com.jan1ooo.workshopmongo.resources;

import com.jan1ooo.workshopmongo.domain.Post;
import com.jan1ooo.workshopmongo.domain.User;
import com.jan1ooo.workshopmongo.dto.UserDTO;
import com.jan1ooo.workshopmongo.resources.util.URL;
import com.jan1ooo.workshopmongo.service.PostService;
import com.jan1ooo.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    public PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = postService.findById(id);
        return ResponseEntity.status(200).body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.status(200).body(list);
    }

}
