package mk.ukim.finki.soa.invoiceservice.utility;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import mk.ukim.finki.soa.invoiceservice.model.Invoice;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InvoicePDFExporter {
    private Invoice invoice;

    public InvoicePDFExporter(Invoice invoice) {
        this.invoice = invoice;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Invoice ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("DateTime", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("OrderId", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Workout Programs", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        table.addCell(String.valueOf(invoice.getId()));
        table.addCell(invoice.getDateTime().toString());
        table.addCell(invoice.getOrderId().toString());
//        table.addCell(user.getRoles().toString());
    }

    public ByteArrayInputStream export() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Invoice details", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());

    }
}
