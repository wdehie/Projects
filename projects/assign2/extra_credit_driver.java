public class extra_credit_driver {
    public static void main(String[] args) {
        MyStringBuilder sb = new MyStringBuilder("hello world world");

        System.out.println("Testing lastIndexOf method:");
        System.out.println("Index of 'world': " + sb.lastIndexOf("world"));
        System.out.println("Index of 'hello': " + sb.lastIndexOf("hello"));
        System.out.println("Index of '': " + sb.lastIndexOf(""));
        System.out.println("Index of 'foo': " + sb.lastIndexOf("foo"));
    }
}
