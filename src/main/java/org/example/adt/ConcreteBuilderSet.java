package org.example.adt;

public class ConcreteBuilderSet implements IBuilderSet{
    private ISet set = new DynamicSet();
    @Override
    public ConcreteBuilderSet add(int a) {
        set.add(a);
        return this;
    }

    @Override
    public IBuilderSet addAll(ISet setRecibido) {
        while (!setRecibido.isEmpty()) {
            int value = setRecibido.choose();
            this.set.add(value);
            setRecibido.remove(value);
        }
        return this;
    }
}
