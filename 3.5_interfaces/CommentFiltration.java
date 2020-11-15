/**
 * Filtration system for comments.
 */

public class CommentFiltration {

    /**
     * abstract class for spam and negative text analyzers
     */
    public static abstract class KeyWordAnalyzer implements TextAnalyzer {
        abstract protected String[] getKeywords();

        abstract protected Label getLabel();

        @Override
        public Label processText(String text) {
            for (int i = 0; i < getKeywords().length; i++) {
                if (text.contains(getKeywords()[i])) {
                    return getLabel();
                }
            }
            return Label.OK;
        }
    }

    /**
     * class for detect spam.
     * it inheritance method for analyze from its superclass.
     */
    static class SpamAnalyzer extends KeyWordAnalyzer {
        private String[] keywords;

        //constructor define set of spam words.
        public SpamAnalyzer(String[] keywords) {
            this.keywords = keywords;
        }

        @Override
        public String[] getKeywords() {
            return keywords;
        }

        @Override
        public Label getLabel() {
            return Label.SPAM;
        }
    }

    /**
     * class for detect negative comments.
     * it has standard set of negative smiles.
     */
     static class NegativeTextAnalyzer extends KeyWordAnalyzer {
        private final String[] negativeSmile = new String[] {":(", "=(", ":|"};

        @Override
        public String[] getKeywords() {
            return negativeSmile;
        }

        @Override
        public Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    /**
     * class for detect too long comments.
     */
    static class TooLongTextAnalyzer implements TextAnalyzer {
        private int maxLength;

        //constructor define the maximum length of comment.
        public TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String text) {
            return text.length() <= this.maxLength ? Label.OK : Label.TOO_LONG;
        }
    }

    /**
     * method for analyzing comment by set of analyzers.
     * @param analyzers set of analyzers
     * @param text comment to analyze
     * @return label for comment
     */
    public static Label checkLabels (TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer element : analyzers) {
            if (element.processText(text) != Label.OK) {
                return element.processText(text);
            }
        }
        return Label.OK;
    }

    public static void main(String[] args) {
        System.out.println(checkLabels(
                new TextAnalyzer[] {
                        new SpamAnalyzer( new String[] {"book"}),
                        new NegativeTextAnalyzer(),
                        new TooLongTextAnalyzer(40)},
                "A mutable sequence of characters."));
    }
}
