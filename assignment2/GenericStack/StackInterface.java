package GenericStack;

interface StackInterface<E extends Copy<E>> { // Copy<E> is the bound, it is an interface
    /*
     * Elements : objects of type E
     * Structure: linear
     * Domain   : all rows of objects of type E
     *
     * There is a default constructor that initializes the stack on the empty
     * stack.
     *
     */
    
    StackInterface<E> init ();
    /* PRE  - 
       POST - The stack is empty.
    */
    
    StackInterface<E> push (E element);
    /* PRE  - 
       POST - A copy of element has been pushed on the top of stack-PRE
              and is thus the top of stack-POST.
    */
    
    E pop ();
    /* PRE  - The stack is not empty
       POST - The top of the stack-PRE is returned and deleted.
    */
    
    E top ();
    /* PRE  - The stack is not empty
       POST - A copy of the top of the stack has been returned.
    */
    
    boolean isEmpty ();
    /* PRE  - 
       POST - true:  The amount of elements of the stack equals 0.
              false: the amount of elements of the stack is greater than 0.
    */
    
    int size ();
    /* PRE  - 
       POST - The amount of elements of the stack has been returned.
    */

    StackInterface<E> copy ();
    /* PRE  - 
       POST - A copy of the stack has been returned.
    */

    StackInterface<E> concat (StackInterface<E> rhs);
    /* PRE  - 
       POST - The concatenation of stack and rhs has been created by putting
              all the elements of rhs, in the same order as these elements had
              in rhs, on the top of stack.
              This concatenation of the stack and rhs has been returned.
    */

}

