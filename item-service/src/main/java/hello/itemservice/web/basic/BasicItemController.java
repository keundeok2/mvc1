package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    @PostConstruct
    public void init() {
        itemRepository.save( new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String add() {
        return "basic/addForm";
    }


//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * @ModelAttribute("item") Item item
     * model.addAttribute("item", item); μλ μΆκ°
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
    //model.addAttribute("item", item); //μλ μΆκ°, μλ΅ κ°λ₯
        return "basic/item";
    }

    /**
     * @ModelAttribute name μλ΅ κ°λ₯
     * model.addAttribute(item); μλ μΆκ°, μλ΅ κ°λ₯
     * μλ΅μ modelμ μ μ₯λλ nameμ ν΄λμ€λͺ μ²«κΈμλ§ μλ¬Έμλ‘ λ±λ‘ Item -> item
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * @ModelAttribute μμ²΄ μλ΅ κ°λ₯
     * model.addAttribute(item) μλ μΆκ°
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        // PathVariableμ λ³μλ₯Ό returnλλ Stringμμ {}λ‘ μ¬μ© κ°λ₯
        return "redirect:/basic/items/{itemId}";
    }


    /*
        Html Form-data μ μ‘μ GET, POSTλ§ μ§μνλ€. PUT, PATCHλ μ§μνμ§ μμ
     */

    /**
     * PRG - Post/Redirect/Get
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * RedirectAttributes
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        // RedirectAttributeλ return Stringμμ {}λ‘ μ¬μ©κ°λ₯
        return "redirect:/basic/items/{itemId}";
    }

}
