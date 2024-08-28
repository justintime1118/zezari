package zezari.zezari_prototype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zezari.zezari_prototype.domain.Image;
import zezari.zezari_prototype.domain.Page;
import zezari.zezari_prototype.domain.User;
import zezari.zezari_prototype.dto.CreatePageRequest;
import zezari.zezari_prototype.service.PageService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pages")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping("/create")
    public String showCreateForm() {
        return "createPage";
    }

    @PostMapping
    public String createPage(@ModelAttribute("page") CreatePageRequest dto, @AuthenticationPrincipal User user, @RequestParam("images") List<MultipartFile> images) throws IOException {
        Page savedPage = pageService.createPage(dto, user, images);
        return "redirect:/pages/" + savedPage.getId();
    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") Long id, Model model) {
        Page page = pageService.findPageById(id);
        List<Image> images = pageService.findImagesForPage(id);
        model.addAttribute("page", page);
        model.addAttribute("images", images);
        return "showPage";
    }
}
