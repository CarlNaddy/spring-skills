package com.example.referenceapp.user;

public record Todo(Long id, Long userId, String text, boolean done) {
}

