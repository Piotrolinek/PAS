package pl.lodz.p.mvc.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lodz.p.mvc.service.implementation.VMachineService;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/vmachine")
@Validated
public class VMachineController {

    private final VMachineService vMachineService;

    @GetMapping
    public String getAllVMachines(Model model) {
        try {
            model.addAttribute("vms", vMachineService.getAllVMachines());
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("currentPage", "/vmachine");
        return "vmachines";
    }

    @GetMapping("/{uuid}")
    public String getVMachine(@PathVariable("uuid") UUID uuid, Model model) {
        model.addAttribute("vm", vMachineService.getVMachine(uuid));
        model.addAttribute("currentPage", "/vmachine/" + uuid);
        return "vmachine";
    }

    @PostMapping("delete/{uuid}")
    public String deleteVMachine(@PathVariable("uuid") UUID uuid, RedirectAttributes redirectAttributes) {
        try {
            vMachineService.deleteVMachine(uuid);
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/vmachine";
    }
//
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("vm", new VMachine());
//        return "vmachine-form"; // Widok vmachine-form.jsp
//    }
//
//    @PostMapping
//    public String createVMachine(@Valid @ModelAttribute("vm") VMachine vm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "vmachine-form";
//        }
//        vMachineService.createVMachine(vm);
//        return "redirect:/vmachine";
//    }
//
//    @GetMapping("/{uuid}/edit")
//    public String showEditForm(@PathVariable("uuid") UUID uuid, Model model) {
//        model.addAttribute("vm", vMachineService.getVMachine(uuid));
//        return "vmachine-form"; // Widok ten sam co dodawanie
//    }
//
//    @PostMapping("/{uuid}/edit")
//    public String updateVMachine(@PathVariable("uuid") UUID uuid, @RequestParam Map<String, String> fieldsToUpdate, Model model) {
//        vMachineService.updateVMachine(uuid, fieldsToUpdate);
//        return "redirect:/vmachine";
//    }
//
//    @PostMapping("/{uuid}/delete")
//    public String deleteVMachine(@PathVariable("uuid") UUID uuid) {
//        vMachineService.deleteVMachine(uuid);
//        return "redirect:/vmachine";
//    }
}
