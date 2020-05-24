#include<cassert>
#include "LinkedList.h"

template<class ItemType>
LinkedList<ItemType>::LinkedList() : headPtr(nullptr), itemCount(0)
{
} // end default constructor


template<class ItemType>
LinkedList<ItemType>::LinkedList(const LinkedList<ItemType>& aList) : itemCount(aList.itemCount) {
	Node<ItemType>* origChainPtr = aList.headPtr;  // Points to nodes in original chain
	if (origChainPtr == nullptr)
		headPtr = nullptr;  // Original list is empty
	else
	{
		// Copy first node
		headPtr = new Node<ItemType>();
		headPtr->setItem(origChainPtr->getItem());
		// Copy remaining nodes
		Node<ItemType>* newChainPtr = headPtr;      // Points to last node in new chain
		origChainPtr = origChainPtr->getNext();     // Advance original-chain pointer
		while (origChainPtr != nullptr)
		{
			// Get next item from original chain
			ItemType nextItem = origChainPtr->getItem();
			// Create a new node containing the next item 
			Node<ItemType>* newNodePtr = new Node<ItemType>(nextItem);
			// Link new node to end of new chain
			newChainPtr->setNext(newNodePtr);
			// Advance pointer to new last node      
			newChainPtr = newChainPtr->getNext();
			// Advance original-chain pointer
			origChainPtr = origChainPtr->getNext();
		}  // end while
		newChainPtr->setNext(nullptr);              // Flag end of chain
	}  // end if
}  // end copy constructor




template<class ItemType>
LinkedList<ItemType>::~LinkedList()
{
	clear();
} // end destructor


template<class ItemType>
void LinkedList<ItemType>::clear()
{
	while (!isEmpty())
		remove(1);
} // end clear


template<class ItemType>
bool LinkedList<ItemType>::remove(int position)
{
	bool ableToRemove = (position >= 1) && (position <= itemCount);
	if (ableToRemove)
	{
		Node<ItemType>* curPtr = nullptr;
		if (position == 1)
		{
			// Remove the first node in the chain
			curPtr = headPtr; // Save pointer to node
			headPtr = headPtr->getNext();
		}
		else
		{
			// Find node that is before the one to remove
			Node<ItemType>* prevPtr = getNodeAt(position - 1);

			// Point to node to remove
			curPtr = prevPtr->getNext();

			// Disconnect indicated node from chain by connecting the
			// prior node with the one after
			prevPtr->setNext(curPtr->getNext());
		} // end if

		  // Return node to system
		curPtr->setNext(nullptr);
		delete curPtr;
		curPtr = nullptr;
		itemCount--; // Decrease count of entries
	} // end if

	return ableToRemove;
} // end remove


template<class ItemType>
ItemType LinkedList<ItemType>::getEntry(int position) const
throw(PrecondViolatedExcept)
{
	// Enforce precondition
	bool ableToGet = (position >= 1) && (position <= itemCount);
	if (ableToGet)
	{
		Node<ItemType>* nodePtr = getNodeAt(position);
		return nodePtr->getItem();
	}
	else
	{
		std::string message = "getEntry() called with an empty list or ";
		message = message + "invalid position.";
		throw(PrecondViolatedExcept(message));
	} // end if
} // end getEntry


template<class ItemType>
Node<ItemType>* LinkedList<ItemType>::getNodeAt(int position) const
{
	// Debugging check of precondition
	assert((position >= 1) && (position <= itemCount));

	// Count from the beginning of the chain
	Node<ItemType>* curPtr = headPtr;
	for (int skip = 1; skip < position; skip++)
		curPtr = curPtr->getNext();

	return curPtr;
}  // end getNodeAt


template<class ItemType>
bool LinkedList<ItemType>::insert(int newPosition, const ItemType& newEntry) {

	bool ableToInsert = (newPosition >= 1) && (newPosition <= itemCount + 1);

	if (ableToInsert) {
		// Create a new node containing the new entry 
		Node<ItemType>* newNodePtr = new Node<ItemType>(newEntry);
		// Attach new node to chain

		if (newPosition == 1) {
			// Insert new node at beginning of chain
			newNodePtr->setNext(headPtr);
			headPtr = newNodePtr;
		}

		else {
			// Find node that will be before new node
			Node<ItemType>* prevPtr = getNodeAt(newPosition - 1);
			// Insert new node after node to which prevPtr points
			newNodePtr->setNext(prevPtr->getNext());
			prevPtr->setNext(newNodePtr);
		}  // end if
		itemCount++;  // Increase count of entries
	}  // end if
	return ableToInsert;

}  // end insert


template<class ItemType>
int LinkedList<ItemType>::getLength() const
{
	return itemCount;
}  // end getLength


template<class ItemType>
bool LinkedList<ItemType>::isEmpty() const
{
	return itemCount == 0;
}  // end isEmpty

template<class ItemType>
ItemType LinkedList<ItemType>::replace(int position, const ItemType& newEntry)
throw(PrecondViolatedExcept)
{
	// Enforce precondition
	bool ableToReplace = (position >= 1) && (position <= itemCount);
	if (ableToReplace)
	{
		Node<ItemType>* nodePtr = getNodeAt(position);
		ItemType Value = nodePtr->getItem();
		nodePtr->setItem(newEntry);
		return Value;
	}
	else
	{
		std::string message = "getEntry() called with an empty list or ";
		message = message + "invalid position.";
		throw(PrecondViolatedExcept(message));
	} // end if
} // end replace

   /*template<class ItemType>
   Node<ItemType>* LinkedList<ItemType>::insertNode(int position, Node<ItemType>* newNodePtr,
   Node<ItemType>* subChainPtr)
   {
   if (position == 1)
   {
   // Insert new node at beginning of subchain
   newNodePtr->setNext(subChainPtr);
   subChainPtr = newNodePtr;
   itemCount++;  // Increase count of entries
   }
   else
   {
   Node<ItemType>* afterPtr = insertNode(position - 1, newNodePtr, subChainPtr->getNext());
   subChainPtr->setNext(afterPtr);
   }  // end if

   return subChainPtr;
   }  // end insertNode
   */
