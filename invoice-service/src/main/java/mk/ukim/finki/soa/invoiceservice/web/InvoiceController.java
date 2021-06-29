package mk.ukim.finki.soa.invoiceservice.web;

import mk.ukim.finki.soa.invoiceservice.model.Invoice;
import mk.ukim.finki.soa.invoiceservice.model.dto.InvoiceDto;
import mk.ukim.finki.soa.invoiceservice.service.InvoiceService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    @GetMapping()
    public List<Invoice> getInvoices() {
        return invoiceService.findAll();
    }

    @PostMapping()
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.createInvoice(invoiceDto);

    }
}
