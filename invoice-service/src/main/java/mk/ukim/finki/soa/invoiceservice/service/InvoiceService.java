package mk.ukim.finki.soa.invoiceservice.service;

import mk.ukim.finki.soa.invoiceservice.model.Invoice;
import mk.ukim.finki.soa.invoiceservice.model.dto.InvoiceDto;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll();
    Invoice findById(Long id);
    InvoiceDto createInvoice(InvoiceDto invoiceDto);

}
