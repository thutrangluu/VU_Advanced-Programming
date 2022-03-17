package GenericStack;

class A implements Copy<A> {

    int i;

    A () {
        this(0);
    }

    A (int i) {
        this.i = i;
    }

    public A copy () {
        return new A(i);
    }
}

