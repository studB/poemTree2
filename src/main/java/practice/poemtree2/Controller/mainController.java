package practice.poemtree2.Controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import practice.poemtree2.Service.ListSetting;
import practice.poemtree2.Service.PomePageService;
import practice.poemtree2.Service.RepoControll;
import practice.poemtree2.domain.FavorInfo;
import practice.poemtree2.domain.ListInfo;
import practice.poemtree2.domain.favors.Form;
import practice.poemtree2.domain.favors.Genre;
import practice.poemtree2.domain.favors.Language;

@Controller
public class mainController {

    private final ObjectProvider<ListSetting> listSettingProvider;
    private final RepoControll repoControll;
    private final PomePageService poemPageServivce;

    @Autowired
    public mainController(ObjectProvider<ListSetting> listSettingProvider, RepoControll repoControll,
            PomePageService poemPageServivce) {
        this.listSettingProvider = listSettingProvider;
        this.repoControll = repoControll;
        this.poemPageServivce = poemPageServivce;
    }

    /*
     * private final ListSetting listSetting;
     * 
     * @Autowired public mainController(ListSetting listSetting) { this.listSetting
     * = listSetting; }
     */

    @GetMapping("/")
    public String favorSelect(Model model) {
        model.addAttribute("genre", Genre.values());
        model.addAttribute("form", Form.values());
        model.addAttribute("language", Language.values());

        return "favorSelect";
    }

    @GetMapping("/back")
    @ResponseBody
    public void backToSelect(@RequestParam("sessionKey") String sessionKey) {
        repoControll.eraseSession(sessionKey);
    }

    /*
     * @GetMapping("/test")
     * 
     * @ResponseBody public String cookieSet(HttpServletResponse resp) { Cookie
     * cookie = new Cookie("key", UUID.randomUUID().toString());
     * resp.addCookie(cookie); System.out.println("Cookie : " + cookie.getValue());
     * return "a"; }
     * 
     * @GetMapping("/test2")
     * 
     * @ResponseBody public String cookieGet(HttpServletRequest rq) { Cookie[] list
     * = rq.getCookies(); for (Cookie c : list) { System.out.println(c.getValue());
     * } return "a"; }
     * 
     * 
     * @PostMapping("/list") public String initGenList(@ModelAttribute FavorInfo
     * favorInfo) { ModelAndView model = new ModelAndView("PoemList"); return
     * "redirect:/PoemList"; }
     */

    @GetMapping("/PoemList")
    public String view(@Nullable @RequestParam("genre") String genre, @Nullable @RequestParam("form") String form,
            @Nullable @RequestParam("language") String language, Model model) {

        FavorInfo favorInfo = new FavorInfo();
        favorInfo.setGenre(genre);
        favorInfo.setForm(form);
        favorInfo.setLanguage(language);
        ListSetting listSetting = listSettingProvider.getObject();
        String sessionKey = listSetting.getSessionKey();
        model.addAttribute("sessionKey", sessionKey);
        model.addAttribute("poemList", listSetting.initCallList(favorInfo));

        return "PoemList";
    }

    /*
     * public ModelAndView initGenList(@ModelAttribute FavorInfo favorInfo) { // *
     * get session key
     * 
     * ListSetting listSetting = listSettingProvider.getObject(); String sessionKey
     * = listSetting.getSessionKey(); ModelAndView model = new
     * ModelAndView("PoemList"); model.addObject("sessionKey", sessionKey);
     * model.addObject("poemList", listSetting.initCallList(favorInfo)); return
     * model; }
     */

    @GetMapping("/refresh/{sessionKey}")
    public String refreshList(@PathVariable("sessionKey") String sessionKey, Model model) {

        model.addAttribute("sessionKey", sessionKey);
        model.addAttribute("poemList", repoControll.refresh(sessionKey));
        /*
         * Consider the situation that viewer go back from individual poem page. No
         * change may be a best option.
         * 
         * 
         * -> Completed
         * 
         * histoty.back() can do it.
         */
        return "PoemList";
    }

    @GetMapping("/poemPage")
    public String clickOnePoem(@RequestParam("cid") String cid, @RequestParam("sessionKey") String sessionKey,
            Model model) {
        Map<String, String> info = poemPageServivce.onePoemInfo(cid);
        model.addAttribute("info", info);
        model.addAttribute("sessionKey2", sessionKey);
        model.addAttribute("cid", cid);
        return "poemPage";
    }

    @GetMapping("/starring")
    @ResponseBody
    public void clickStar(@RequestParam("cid") String cid, @RequestParam("star") String star) {
        int intstar = Integer.parseInt(star);
        poemPageServivce.plusStar(cid, intstar);
    }

}
