package com.yevheniimakar.beltcutting.controller.piece;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.piece.request.PieceCreateList;
import com.yevheniimakar.beltcutting.model.piece.response.PieceResponseViewInList;
import com.yevheniimakar.beltcutting.service.PieceService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.PIECES)
public class PieceController {

    private final PieceService pieceService;

    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @GetMapping("/card/{id}")
    public List<PieceResponseViewInList> getPiecesByCardId(@PathVariable Long id, Authentication authentication) {
        return pieceService.getPieceListByCardId(id);
    }

    @PostMapping("/card/{id}")
    public List<PieceResponseViewInList> createPiecesByCardId(@PathVariable Long id, @RequestBody PieceCreateList request, Authentication authentication) {
        return pieceService.savePieceListCardId(request.getPieceRequestCreateList(), id);
    }

}
