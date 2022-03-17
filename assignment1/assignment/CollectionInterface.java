package assignment;

public interface CollectionInterface {

	/** ADT for the class CollectionInterface.
	 *
	 * @author : Leroy Truong & Radu Sibechi
	 *
	 * @elements :  strings of the type Identifier
	 * @structure : none
	 * @domain :    can only contain up to 20 elements
	 *
	 * @constructor
	 *	Collection();
	 *	    @precondition
	 *           -
	 *		@postcondition
	 *           The new Collection-object contains only the empty collection.
	 *
	 *	Collection (Collection src);
	 *      @precondition
	 *           -
	 *		@postcondition
	 *           The new Collection-object contains a copy of the parameter src.
	 **/

	/**
	 * Initializes the Collection-object to the empty collection.
	 *
	 * @precondition    -
	 * @postcondition   The collection is empty.
	 **/
	CollectionInterface init();

	/**
	 * Adds an element to the collection.
	 *
	 * @precondition    The collection must not have more than 20 elements.
	 * @postcondition   The element is in the collection.
	 **/
	void add(Identifier element);

	/**
	 * Returns one element of the collection.
	 *
	 * @precondition    The collection is not empty.
	 * @postcondition   Returns an element.
	 **/
	Identifier get();

	/**
	 * Returns whether the collection is empty.
	 *
	 * @precondition    -
	 * @postcondition   TRUE: the number of elements in the collection == 0.
	 *                  FALSE: the number of elements in the collection > 0.
	 **/
	boolean isEmpty();

	/**
	 * Returns the amount of elements of the collection.
	 *
	 * @precondition    -
	 * @postcondition   The amount of elements of the collection is returned.
	 **/
	int size();

	/**
	 * Returns whether identifier is in the collection.
	 *
	 * @precondition    -
	 * @postcondition   TRUE: the identifier is in the collection.
	 *                  FALSE: the identifier is not in the collection.
	 **/
	boolean contains(Identifier identifier);

	/**
	 * Returns the identifiers in a String separated by spaces.
	 *
	 * @precondition    Collection is not empty.
	 * @postcondition   A String returned containing all identifiers in the collection separated by spaces.
	 **/
	String toString();

	/**
	 * Returns a new Collection-object containing identifiers which are present in the collection but absent in the parameter.
	 *
	 * @precondition    -
	 * @postcondition   Collection-object returned containing identifiers which are present in the collection but absent in the parameter.
	 **/
	Collection calculateDifference(Collection collection);

	/**
	 * Returns a new Collection-object containing identifiers which are present in both the collection and parameter.
	 *
	 * @precondition    -
	 * @postcondition   Collection-object returned containing identifiers which are present in both the collection and parameter.
	 **/
	Collection calculateIntersection(Collection collection);

	/**
	 * Returns a new Collection-object containing all distinct identifiers from the collection and parameter.
	 *
	 * @throws          Exception if the new Collection-object exceeds 20 elements.
	 * @postcondition   Collection-object returned containing all distinct identifiers from the collection and parameter.
	 **/
	Collection calculateUnion(Collection collection) throws Exception;

	/**
	 * Returns a new Collection-object containing identifiers which are present in the collection but not in the parameter
	 * and identifiers which are present in the parameter but not in the collection.
	 *
	 * @precondition    -
	 * @postcondition   Collection-object returned containing identifiers which are present in the collection but not in the parameter
	 *                  and identifiers which are present in the parameter but not in the collection.
	 **/
	Collection calculateSymDiff(Collection collection);

}