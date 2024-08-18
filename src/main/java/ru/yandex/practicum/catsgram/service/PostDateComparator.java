package ru.yandex.practicum.catsgram.service;

import ru.yandex.practicum.catsgram.controller.SortOrder;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.Comparator;

public class PostDateComparator implements Comparator<Post> {

    private final SortOrder sortOrder;

    public PostDateComparator(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Post post1, Post post2) {

        switch (sortOrder) {
            case ASCENDING -> {
                if (post1.getPostDate().isAfter(post2.getPostDate())) {
                    return 1;
                } else {
                    return -1;
                }
            }
            case DESCENDING -> {
                if (post1.getPostDate().isBefore(post2.getPostDate())) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }
}
