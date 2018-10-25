package seedu.address.model.user;

import seedu.address.model.restaurant.Name;
import seedu.address.model.restaurant.Rating;
import seedu.address.model.restaurant.WrittenReview;

/**
 * Represents a Restaurant's review written by a user in the address book.
 */
public class RestaurantReview {

    private Name restaurantName;
    private Rating rating;
    private WrittenReview review;

    /**
     * Construcrs a {@code RestaurantReview}.
     * 
     * @param restaurantName a valid restaurant name.
     * @param rating a valid rating.
     * @param review a written review.
     */
    public RestaurantReview(Name restaurantName, Rating rating, WrittenReview review) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.review = review;
    }

    public String getResturantName() {
        return restaurantName.fullName;
    }

    public int getRating() {
        return rating.rating;
    }

    public String getWrittenReview() {
        return review.writtenReview;
    }
}
