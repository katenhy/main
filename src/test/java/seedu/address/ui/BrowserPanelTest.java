package seedu.address.ui;

import static seedu.address.testutil.TypicalRestaurants.RESTAURANT_A;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.BrowserPanelHandle;
import seedu.address.commons.events.ui.RestaurantPanelSelectionChangedEvent;

public class BrowserPanelTest extends GuiUnitTest {
    private RestaurantPanelSelectionChangedEvent selectionChangedEventStub;

    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStub = new RestaurantPanelSelectionChangedEvent(RESTAURANT_A);

        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Test
    public void display() throws Exception {
        // default web page
        //URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        //assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        // associated web page of a restaurant
        //postNow(selectionChangedEventStub);
        //URL expectedRestaurantUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);

        //waitUntilBrowserLoaded(browserPanelHandle);
        //assertEquals(expectedRestaurantUrl, browserPanelHandle.getLoadedUrl());
    }
}
