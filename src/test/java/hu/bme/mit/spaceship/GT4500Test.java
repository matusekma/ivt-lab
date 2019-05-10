package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimaryTs;
  private TorpedoStore mockSecondaryTs;

  @BeforeEach
  public void init(){
    // new GT4500 spaceship
    mockPrimaryTs = mock(TorpedoStore.class);
    mockSecondaryTs = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimaryTs, mockSecondaryTs);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    when(mockPrimaryTs.isEmpty()).thenReturn(false);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPrimaryTs, times(1)).fire(1);
    verify(mockSecondaryTs, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(false);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPrimaryTs, times(1)).fire(1);
    verify(mockSecondaryTs, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Empty(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(true);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(true);
    when(mockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPrimaryTs, times(0)).fire(1);
    verify(mockSecondaryTs, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(false);
    when(mockPrimaryTs.fire(1)).thenReturn(false);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPrimaryTs, times(1)).fire(1);
    verify(mockSecondaryTs, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Empty(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(true);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(true);
    when(mockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPrimaryTs, times(0)).fire(1);
    verify(mockSecondaryTs, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Primary_Empty(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(true);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPrimaryTs, times(0)).fire(1);
    verify(mockSecondaryTs, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Primary_Failure(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(false);
    when(mockPrimaryTs.fire(1)).thenReturn(false);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPrimaryTs, times(1)).isEmpty();
    verify(mockSecondaryTs, times(0)).isEmpty();
    verify(mockPrimaryTs, times(1)).fire(1);
    verify(mockSecondaryTs, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_First_Empty_Secondary_Failure(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(true);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPrimaryTs, times(1)).isEmpty();
    verify(mockSecondaryTs, times(1)).isEmpty();
    verify(mockPrimaryTs, times(0)).fire(1);
    verify(mockSecondaryTs, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice(){
    // Arrange
    when(mockPrimaryTs.isEmpty()).thenReturn(false);
    when(mockPrimaryTs.fire(1)).thenReturn(true);

    when(mockSecondaryTs.isEmpty()).thenReturn(false);
    when(mockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);

    verify(mockPrimaryTs, times(1)).fire(1);
    verify(mockSecondaryTs, times(1)).fire(1);
  }

}
