package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Collection<Post> findAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") long from,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        if (size < 0) {
            throw new ConditionsNotMetException("некорректный параметр запроса size " + size);
        }

        SortOrder sortOrder = SortOrder.from(sort);
        return postService.findAll(size, from, sortOrder);
    }

    @GetMapping("/{id}")
    public Post findUserById(@PathVariable long id) {
        Optional<Post> optionalPost = postService.findPostById(id);
        if (optionalPost.isEmpty()) {
            throw new ConditionsNotMetException("Пост с id = " + id + " не найден");
        } else {
            return optionalPost.get();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post newPost) {
        return postService.create(newPost);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }

}
