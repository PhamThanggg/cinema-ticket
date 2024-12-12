package com.example.cinematicket.services.invoiceItem;

import com.example.cinematicket.dtos.requests.InvoiceItemRequest;
import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.Invoice;
import com.example.cinematicket.entities.InvoiceItem;
import com.example.cinematicket.entities.Item;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.repositories.CinemaRepository;
import com.example.cinematicket.repositories.InvoiceItemRepository;
import com.example.cinematicket.repositories.InvoiceRepository;
import com.example.cinematicket.repositories.ItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceItemService implements IInvoiceItemService{
    InvoiceItemRepository invoiceItemRepository;
    ItemRepository itemRepository;
    CinemaRepository cinemaRepository;
    InvoiceRepository invoiceRepository;
    
    @Override
    public List<InvoiceItem> create(Set<InvoiceItemRequest> requests, Long cinemaId, Long invoiceId) {

        if(!cinemaRepository.existsById(cinemaId))
                throw new AppException(ErrorCode.CINEMA_NOT_EXISTED);

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXISTS));

        Set<Long> itemIds = new HashSet<>();
        for(InvoiceItemRequest request : requests){
            itemIds.add(request.getId());
        }

        // Truy vấn tất cả các đối tượng từ cơ sở dữ liệu
        Map<Long, Item> itemMap = itemRepository.findByIdInAndIdCinema(itemIds, cinemaId).stream()
                .collect(Collectors.toMap(Item::getId, item -> item));

        // Kiểm tra và xử lý lỗi
        for (InvoiceItemRequest request : requests) {
            // Kiểm tra sự tồn tại của từng đối tượng
            if (!itemMap.containsKey(request.getId())) {
                throw new RuntimeException("Item with ID " + request.getId() + " not exists");
            }
        }

        List<InvoiceItem> invoiceItems = requests.stream()
                .map(request -> {
                    // Lấy item từ itemMap theo itemId trong request
                    Item item = itemMap.get(request.getId());;
                    // Tạo InvoiceItem
                    return InvoiceItem.builder()
                            .invoice(invoice)
                            .item(item) // Gán item từ itemMap
                            .quantity(request.getQuantity()) // Lấy số lượng từ request
                            .build();
                })
                .collect(Collectors.toList());
        invoiceItemRepository.saveAll(invoiceItems);

        return invoiceItems;
    }
}
