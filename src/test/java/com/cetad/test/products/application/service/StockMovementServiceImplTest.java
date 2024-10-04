package com.cetad.test.products.application.service;

import com.cetad.test.products.application.port.out.StockMovementRepository;
import com.cetad.test.products.domain.model.StockMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceImplTest {

    @Mock
    private StockMovementRepository stockMovementRepository;

    @InjectMocks
    private StockMovementServiceImpl stockMovementServiceImpl;

    private List<StockMovement> stockMovements;

    @BeforeEach
    void setUp() {
        stockMovements = Arrays.asList(
                new StockMovement(1L, 1L, "entry", LocalDateTime.now().minusDays(1), 10),
                new StockMovement(2L, 1L, "exit", LocalDateTime.now().minusHours(5), 3),
                new StockMovement(3L, 2L, "entry", LocalDateTime.now().minusDays(2), 15)
        );
    }

    @Test
    void getStockMovementForProductId() {
        Long productId = 1L;

        when(stockMovementRepository.findByProductId(productId)).thenReturn(Arrays.asList(stockMovements.get(0), stockMovements.get(1)));

        List<StockMovement> result = stockMovementServiceImpl.getStockMovementForProductId(productId);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("entry", result.get(0).getMovementType());
        assertEquals("exit", result.get(1).getMovementType());
        assertEquals(10, result.get(0).getQuantity());
        assertEquals(3, result.get(1).getQuantity());

        verify(stockMovementRepository).findByProductId(productId);
    }
}