package ec.edu.espol.List;

public interface List<E> extends Iterable{

    public boolean addFirst(E e); // inserta el elemento e al inicio

    public boolean addLast(E e); // inserta el elemento e al final

    public boolean add(int index, E element);

    public E remove(int index);

    public E get(int index);

    public E set(int index, E element);

;    public int size();

    public boolean isEmpty();

    public void clear();

    @Override
    public String toString();

}
