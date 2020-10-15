package seedu.address.model.task;

public abstract class Link {

    /** A brief description of the link. */
    private String description;

    /** The url for the link */
    private String url;

    /**
     * Constructs a link with description and url.
     *
     * @param description a brief description of the link
     * @param url the url of the link
     */
    public Link(String description, String url) {
        this.description = description;
        this.url = url;
    }

    /**
     * Returns the description of the link as a string.
     *
     * @return the description of the link.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the url of the link as a string.
     *
     * @return the url of the link.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a boolean value indicating if the link is equal to
     * another object by determining if descriptions and url
     * are equal.
     *
     * @param o an object that is compared to the link to determine if both are equal
     * @return true or false if the object is equal or not equal to the link respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Link) {
            Link link = (Link) o;
            boolean isEqualLink = this.description.equals(link.description) && this.url == link.url;
            return isEqualLink;
        } else {
            return false;
        }
    }
}
