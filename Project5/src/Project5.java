import java.util.*;

public class Project5<E>{
    static LBTreeV2<Character> tree = new LBTreeV2<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] arrOfInput = input.split(" ", 2);
            String type = arrOfInput[0];        									// Contains the type of expression
            String expression = arrOfInput[1];  									// Contains the expression
            
            if (type.equals("!")) {  												// prefix expression -> postfix expression
                StringTokenizer tokenizer = new StringTokenizer(expression, " ");
                Position<Character> root = buildFromPrefix(tokenizer);
                String postfix = postfixExpression(root);
                System.out.println(postfix);
                printTree(root);
                
            } else if (type.equals("@")) {  										// postfix expression -> prefix expression
                String reversed = new StringBuilder(expression).reverse().toString();
                StringTokenizer tokenizer = new StringTokenizer(reversed, " ");
                Position<Character> root = buildFromPostfix(tokenizer);
                String prefix = prefixExpression(root);
                System.out.println(prefix);
                printTree(root);
                
            } else {
                System.out.println("Invalid input format. Please use either '!' or '@' as the first character.");
            }
        }
        scanner.close();
    }

    // Builds a binary tree from a prefix expression
    private static Position<Character> buildFromPrefix(StringTokenizer tokenizer) {
        if (tokenizer.hasMoreTokens()) {
            char token = tokenizer.nextToken().charAt(0);
            if (isOperator(token)) {
                Position<Character> node = tree.createNode(token, null);
                tree.addLeft(node, buildFromPrefix(tokenizer));
                tree.addRight(node, buildFromPrefix(tokenizer));
                return node;
            } else {
                return tree.createNode(token, null);
            }
        } else {
            return null;
        }
    }
    private static <E> void printSubtree(LinkedBinaryTree<E> tree, Position<E> node, int depth) {
        if (node != null) {
            printSubtree(tree, tree.right(node), depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("    ");
            }
            System.out.println(node.getElement());
            printSubtree(tree, tree.left(node), depth + 1);
        }
    }

    public static <E> void printTree(Position<E> root) {
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.addRoot(root.getElement());
        printSubtree(tree, root, 0);
    }

    
    // Builds a binary tree from a postfix expression
    private static Position<Character> buildFromPostfix(StringTokenizer tokenizer) {
        if (tokenizer.hasMoreTokens()) {
            char token = tokenizer.nextToken().charAt(0);
            if (isOperator(token)) {
                Position<Character> node = tree.createNode(token, null);
                tree.addRight(node, buildFromPostfix(tokenizer));
                tree.addLeft(node, buildFromPostfix(tokenizer));
                return node;
            } else {
                return tree.createNode(token, null);
            }
        } else {
            return null;
        }
    }
    
    // Converts a binary tree to a postfix expression
    private static String postfixExpression(Position<Character> node) {
        if (node == null) {
            return "";
        }
        String left = postfixExpression(tree.left(node));
        String right = postfixExpression(tree.right(node));
        return left + right + node.getElement();
    }
    
    // Converts a binary tree to a prefix expression
    private static String prefixExpression(Position<Character> node) {
        if (node == null) {
            return "";
        }
        String left = prefixExpression(tree.left(node));
        String right = prefixExpression(tree.right(node));
        return node.getElement() + left + right;
    }
    
    // Checks if a character is an operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
 

}
