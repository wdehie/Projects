public class extra_credit_driver {
    public static void main(String[] args) {
        MyStringBuilder2 sb = new MyStringBuilder2("hello world world");

        System.out.println("Testing lastIndexOf method:");
        System.out.println("Index of 'world': " + sb.lastIndexOf("world"));
        System.out.println("Index of 'hello': " + sb.lastIndexOf("hello"));
        System.out.println("Index of '': " + sb.lastIndexOf(""));
    }
}
