package com.ebay.feed.util;

import com.ebay.feed.exceptions.ClientResponseException;
import com.ebay.feed.exceptions.FeedTokenException;
import com.ebay.feed.util.FeedUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FeedUtilTest {

    @Mock
    private FeedUtil feedUtil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should generate correct range list when given range value and content size")
    public void shouldGenerateCorrectRangeList() {
        String rangeValue = "10240000";
        long contentSize = 102400000L;
        List<String> expectedRangeList = List.of("bytes=0-10240000",
            "bytes=10240001-20480000", "bytes=20480001-30720000");

        when(feedUtil.getRangeList(rangeValue, contentSize)).thenReturn(
            expectedRangeList);

        List<String> actualRangeList = feedUtil.getRangeList(rangeValue,
            contentSize);

        assertEquals(expectedRangeList, actualRangeList);
    }

    @Test
    @DisplayName("Should throw FeedTokenException when unable to get token")
    public void shouldThrowFeedTokenExceptionWhenUnableToGetToken()
        throws FeedTokenException {
        when(feedUtil.getToken()).thenThrow(FeedTokenException.class);

        assertThrows(FeedTokenException.class, () -> feedUtil.getToken());
    }

    @Test
    @DisplayName("Should throw ClientResponseException when unable to get content range")
    public void shouldThrowClientResponseExceptionWhenUnableToGetContentRange()
        throws ClientResponseException, FeedTokenException {
        String marketplaceId = "EBAY_US";
        String baseURL = "https://api.ebay.com/buy/feed/v1_beta/item";

        when(feedUtil.getContentRange(marketplaceId, baseURL)).thenThrow(
            ClientResponseException.class);

        assertThrows(ClientResponseException.class,
            () -> feedUtil.getContentRange(marketplaceId, baseURL));
    }
}
