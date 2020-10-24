package kr.co.softcampus.tooksampoom.Utils;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {
    private int _limit;

    public LimitedQueue(int limit) {
        this._limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > _limit) {
            super.remove();
        }
        return true;
    }
}
