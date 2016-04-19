package com.shredder.utils;

import java.util.ArrayList;
import java.util.List;

public class CircularBuffer<T> {
    private ArrayList<T> buffer;
    private int tail;
    private int head;

    public CircularBuffer(int bufferSize) {
        buffer = new ArrayList<>(bufferSize);
        tail = 0;
        head = 0;
    }

    public void add(T object) {
        buffer.set(head++, object);
        head = head % buffer.size();
        //Push the tail ahead of the head.
        //This means data is lost.
        if (head == tail) {
            take();
        }
    }

    public T take() {
        T t = buffer.get(tail++);
        tail = tail % buffer.size();
        return t;
    }

    public List<T> takeAll() {
        List<T> buffer = new ArrayList<>();
        T t = take();
        while (t != null) {
            buffer.add(t);
            t = take();
        }
        return buffer;
    }
}
