package com.phase2;

import org.junit.Test;

import java.awt.Graphics;
import java.awt.Rectangle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BushTest {

    @Test
    public void testConstructorValidResource() {
        // Test constructor with a valid resource
        Bush bush = new Bush(100, 100);
        assertNotNull("Bush image should be loaded successfully", bush);
    }

    @Test
    public void testRenderWithValidImage() {
        Bush bush = new Bush(100, 100);

        // Mock Graphics object
        Graphics mockGraphics = mock(Graphics.class);

        // Call render and verify no exceptions
        bush.render(mockGraphics);

        // Verify interactions with the mock, like drawing the image
        verify(mockGraphics).drawImage(any(), eq(100), eq(100), eq(30), eq(30), eq(null));
    }

    @Test
    public void testRenderWithNullImage() {
        Bush bush = new Bush(100, 100);

        // Use reflection to set `bushImage` to null
        try {
            java.lang.reflect.Field imageField = Bush.class.getDeclaredField("bushImage");
            imageField.setAccessible(true);
            imageField.set(bush, null);
        } catch (Exception e) {
            fail("Failed to set bushImage to null for testing");
        }

        // Mock Graphics object
        Graphics mockGraphics = mock(Graphics.class);

        // Call render and verify no exceptions
        bush.render(mockGraphics);

        // Verify that no interaction with the Graphics object occurs when `bushImage` is null
        verify(mockGraphics, never()).drawImage(any(), anyInt(), anyInt(), anyInt(), anyInt(), any());
    }

    @Test
    public void testGetBounds() {
        Bush bush = new Bush(100, 100);
        Rectangle bounds = bush.getBounds();

        // Expecting 32x32, as defined in OBJECT_SIZE
        assertEquals("Bounding rectangle width should be correct", 32, bounds.width);
        assertEquals("Bounding rectangle height should be correct", 32, bounds.height);
    }
}
