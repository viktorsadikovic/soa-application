package mk.ukim.finki.soa.invoiceservice.service.impl;

import mk.ukim.finki.soa.invoiceservice.model.Invoice;
import mk.ukim.finki.soa.invoiceservice.model.dto.InvoiceDto;
import mk.ukim.finki.soa.invoiceservice.repository.InvoiceRepository;
import mk.ukim.finki.soa.invoiceservice.service.InvoiceService;
import mk.ukim.finki.soa.invoiceservice.utility.InvoicePDFExporter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = Invoice.create(invoiceDto);

        invoice = invoiceRepository.save(invoice);
//        InvoicePDFExporter exporter = new InvoicePDFExporter(invoice);
//        exporter.export();

        return invoiceDto;
    }
}
