package fr.myhome.server.dto;

import java.util.ArrayList;
import java.util.List;

public abstract class Transformer<I,O> {

    abstract O transform(I input);

    public List<O> transformAll(final List<I> inputList) {
        final List<O> outpuList = new ArrayList<>();
        inputList.forEach(input -> outpuList.add(this.transform(input)));
        return outpuList;
    }

}

