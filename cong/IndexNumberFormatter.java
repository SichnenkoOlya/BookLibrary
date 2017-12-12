package bsuir.library.cong;

public interface IndexNumberFormatter {

    /**
     * Given an index number of menu items in a menu (starting from 0), format it to a string.
     *
     * @param index the index number of a menu item in a menu (starting from 0)
     * @return a string that is to be printed in the same line as, and in front of the menu time description
     */
    String format(int index);

}