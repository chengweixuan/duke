public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toPrint() {
        return "[T]" + super.toPrint();
    }
}
