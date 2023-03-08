package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository; // 필드 주입

    // 재고 저장
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // 변경 감지 기능 사용, Merge -> 변경 감지 기능 지향, Merge 지양 ->
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){

        Item findItem = itemRepository.findOne(itemId);
        // findItem.change(price, name, stockQUantity); // 의미있는 메소드로 set update -> 변경감지
        findItem.setPrice(price); // update 항목만 set 설정한다(변경감지)
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    // 재고 리스트 조회
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    // 재고 단건 조회
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
