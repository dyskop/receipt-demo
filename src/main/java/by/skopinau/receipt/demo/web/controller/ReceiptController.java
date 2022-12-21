package by.skopinau.receipt.demo.web.controller;

import by.skopinau.receipt.demo.dal.entity.Receipt;
import by.skopinau.receipt.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/check")
    public ResponseEntity<List<Receipt>> getByProductsAndCard(
            @RequestParam List<Integer> productId,
            @RequestParam(defaultValue = "0") Integer card) {
        return receiptService.findByProductsAndCard(productId, card)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Receipt> getAll() {
        return receiptService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable int id) {
        return receiptService.findById(id)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}