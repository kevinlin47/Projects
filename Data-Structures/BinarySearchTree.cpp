#include<iostream>

struct node
{
    int data; //data stored in node
    node *left=NULL; //pointer to left child
    node *right=NULL; //pointer to right child
};
node *root=NULL; //pointer to root of tree

node* insertNode(node*,int);
node* deleteNode(node*,int);
bool searchNode(node*,int);
void inOrder(node* );
node* findMin(node*);


int main()
{
std::cout<<"Testing of every function"<<std::endl;
std::cout<<"Inserting 5,3,1,6,7,8 in the tree respectively"<<std::endl;
root=insertNode(root,5);
root=insertNode(root,3);
root=insertNode(root,1);
root=insertNode(root,6);
root=insertNode(root,7);
root=insertNode(root,8);

std::cout<<"\nCalling the inOrder function"<<std::endl;
inOrder(root);

std::cout<<"\nSearching for 10 in tree"<<std::endl;
if (searchNode(root,10)==true)
{
    std::cout<<"\nFound"<<std::endl;
}
else
{
    std::cout<<"\nNot found"<<std::endl;
}

std::cout<<"\nSearching for 6 in the tree"<<std::endl;
if(searchNode(root,6)==true)
{
    std::cout<<"\nFound"<<std::endl;
}
else
{
    std::cout<<"\nNot found"<<std::endl;
}

std::cout<<"\nDeleting 5 from the tree"<<std::endl;
root=deleteNode(root,5);
inOrder(root);

std::cout<<"\nDeleting 8 from the tree"<<std::endl;
root=deleteNode(root,8);
inOrder(root);

std::cout<<"\nDeleting 19 from the tree (19 is not in the tree)"<<std::endl;
inOrder(root);

std::cout<<"\nTesting demonstration complete, program terminating"<<std::endl;

return 0;
}

 //function to insert new node into the tree
node* insertNode(node* root,int n)
{
    //if the tree is empty make the new node the root
    if (root==NULL)
    {
        node* newNode=new node;
        newNode->data=n;
        newNode->left=NULL;
        newNode->right=NULL;
        return newNode;
    }

    //If the data in the root node is less than the item to be inserted
    //go to the right subtree
    if (root->data<n)
    {
        root->right=insertNode(root->right,n);
    }

    //if the data in the root node is greater than the item to be inserted
    //go to the left subtree
    else
    {
        root->left=insertNode(root->left,n);
    }


}

//Display Tree in in order
void inOrder(node* root)
{
    if (root!=NULL)
    {
        inOrder(root->left);
        std::cout<<root->data<<" ";
        inOrder(root->right);
    }
}

//Function to search for number in tree
//@parameters pointer to root of tree, and the number to search for
//returns true if found, false otherwise
bool searchNode(node* root,int key)
{
    //while the root does not point to null
    if (root!=NULL)
    {
        // if the key equals the root, number is found
        if (key==root->data)
        {
            //return true since number is found
            return true;
        }

        //if key is larger than or equal to the root value search the right of the tree
        else if (key>=root->data)
        {
            return searchNode(root->right,key);
        }

        //otherwise search the left of the tree
        else
        {
            return searchNode(root->left,key);
        }
    }
}



//function to delete a node from the tree
node* deleteNode(node* root,int toDelete)
{
    // if the root of the tree is null then the tree is empty
    if (root==NULL)
    {
        std::cout<<"\nTree is empty"<<std::endl;
        return root;
    }

    //if the node is not found in the tree inform the user
    if(searchNode(root,toDelete)==false)
    {
        std::cout<<"\nNode not found"<<std::endl;
        return root;
    }

    //if the node is less than the root value search left of the tree
    else if (toDelete<root->data)
    {
        root->left=deleteNode(root->left,toDelete);
    }

    //if the node is larger than the root value search the right of the tree
    else if (toDelete>root->data)
    {
        root->right=deleteNode(root->right,toDelete);
    }

    //otherwise the node value is equal to the current root
    //and we check to see if the node to delete is a leaf, or has 1 or 2 children
    else
    {
        //if node has no children
        if (root->left==NULL && root->right==NULL)
        {
            root=NULL;
        }

        //if node has one child
        else if (root->left==NULL)
        {

            root=root->right;
        }
        else if (root->right==NULL)
        {
            root=root->left;
        }

        //if node has two children
        else
        {
            node* temp=findMin(root->right);
            root->data=temp->data;
            root->right=deleteNode(root->right,temp->data);
        }
    }

    return root;
}

//function for the delete node function
//to find the smallest value in the right of the tree
node* findMin(node* root)
{
    while(root->left!=NULL)
    {
        root=root->left;
    }

    return root;
}


