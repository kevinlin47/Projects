package pks.bst;
import java.util.*;

public class BSTDriver {

	public static void main(String[] args) {
		BST myTree=new BST();
		
		Random rn=new Random();
		
		for (int i=0;i<10;++i)
		{
			int randomValue=rn.nextInt(100);
			myTree.add(randomValue);
			System.out.println(randomValue);
		}
		System.out.println();
		myTree.inOrder();
		System.out.println();
		myTree.preOrder();
		System.out.println();
		myTree.postOrder();
		
		Scanner in=new Scanner(System.in);
		int toSearch=in.nextInt();
		
		myTree.remove(toSearch);
		System.out.println();
		myTree.inOrder();

	}

}
