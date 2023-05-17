package com.itransition.backend.controller;

import com.itransition.backend.controller.view.LikeCountView;
import com.itransition.backend.controller.view.UserLikeResultView;
import com.itransition.backend.dto.UserCommentDto;
import com.itransition.backend.dto.UserDto;
import com.itransition.backend.dto.UserPostDto;
import com.itransition.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Tag(name = "User Controller")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private static final String ID = "/{id}";
    private final UserService userService;

    @Operation(summary = "Get Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return List<UserDto>",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(userService.getUsers(page, size));
    }

    @Operation(summary = "Get User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return UserDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Save User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return UserDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Validated UserDto userDto) {
        userDto = userService.saveUser(userDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(userDto.getId()).toUri()).body(userDto);
    }

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Return UserDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PutMapping("{userId}")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody @Validated UserDto userDto) {
        return ResponseEntity.accepted().body(userService.updateUser(userId, userDto));
    }

    @Operation(summary = "Update User password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Return HttpStatus",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PutMapping("{userId}/password")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId) or hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long userId, @RequestBody @NotBlank String password) {
        userService.updatePassword(userId, password);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Return HttpStatus",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @DeleteMapping("{userId}")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId) or hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get UserPosts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return UserPostDtos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPostDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("{userId}/posts")
    public ResponseEntity<List<UserPostDto>> getUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserPostsByUserId(userId));
    }

    @Operation(summary = "Save UserPost")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return UserPostDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPostDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PostMapping("{userId}/posts")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<UserPostDto> saveUserPost(@PathVariable Long userId,
                                                    @RequestBody @Validated UserPostDto userPostDto) {
        userPostDto = userService.saveUserPost(userId, userPostDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(userPostDto.getId()).toUri()).body(userPostDto);
    }

    @Operation(summary = "Update UserPost")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Return UserPostDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPostDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PutMapping("{userId}/posts")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<UserPostDto> updateUserPost(@PathVariable Long userId, @RequestBody @Validated String content) {
        return ResponseEntity.accepted().body(userService.updateUserPost(userId, content));
    }

    @Operation(summary = "Delete UserPost")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Return HttpStatus",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @DeleteMapping("posts/{userPostId}")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<HttpStatus> deleteUserPost(@PathVariable Long userPostId) {
        userService.deleteUserPost(userPostId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get UserComments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return UserCommentDtos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCommentDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("{userId}/comments")
    public ResponseEntity<List<UserCommentDto>> getUserCommentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserCommentsByUserId(userId));
    }

    @Operation(summary = "Get UserComments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return UserCommentDtos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCommentDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("posts/{userPostId}/comments")
    public ResponseEntity<List<UserCommentDto>> getUserCommentsByUserPostId(@PathVariable Long userPostId) {
        return ResponseEntity.ok(userService.getUserCommentsByUserPostId(userPostId));
    }

    @Operation(summary = "Save UserComment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return UserPostDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPostDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PostMapping("{userId}/posts/{userPostId}/comments")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<UserCommentDto> saveUserComment(@PathVariable Long userId, @PathVariable Long userPostId,
                                                          @RequestBody @Validated UserCommentDto userCommentDto) {
        userCommentDto = userService.saveUserComment(userId, userPostId, userCommentDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(userCommentDto.getId()).toUri()).body(userCommentDto);
    }

    @Operation(summary = "Update UserComment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Return UserCommentDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCommentDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PutMapping("comments/{userCommentId}")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<UserCommentDto> updateUserComment(@PathVariable Long userCommentId, @RequestBody @Validated String comment) {
        return ResponseEntity.accepted().body(userService.updateUserComment(userCommentId, comment));
    }

    @Operation(summary = "Delete UserComment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Return HttpStatus",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @DeleteMapping("comments/{userCommentId}")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<HttpStatus> deleteUserComment(@PathVariable Long userCommentId) {
        userService.deleteUserComment(userCommentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get UserLikesCount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return LikeCountView",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LikeCountView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("{userId}/likes/count")
    public ResponseEntity<LikeCountView> getUserUserLikeCountByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserUserLikeCountByUserId(userId));
    }

    @Operation(summary = "Get UserLikesCount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return LikeCountView",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LikeCountView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("posts/{userPostId}/likes/count")
    public ResponseEntity<LikeCountView> getUserUserLikeCountByUserPostId(@PathVariable Long userPostId) {
        return ResponseEntity.ok(userService.getUserUserLikeCountByUserPostId(userPostId));
    }

    @Operation(summary = "Get UserLikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return UserLikeResultViews",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLikeResultView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("{userId}/likes")
    public ResponseEntity<List<UserLikeResultView>> getAllUserLikeResultByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getAllUserLikeResultByUserId(userId));
    }

    @Operation(summary = "Get UserLikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return UserLikeResultViews",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLikeResultView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @GetMapping("posts/{userPostId}/likes")
    public ResponseEntity<List<UserLikeResultView>> getAllUserLikeResultUserPostId(@PathVariable Long userPostId) {
        return ResponseEntity.ok(userService.getAllUserLikeResultUserPostId(userPostId));
    }

    @Operation(summary = "Update UserДшлу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Return UserCommentDto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PutMapping("{userId}/posts/{userPostId}")
    @PreAuthorize("@userSecurity.hasUserId(authentication, #userId)")
    public ResponseEntity<Boolean> updateUserLike(@PathVariable Long userId, @PathVariable Long userPostId,
                                                  @RequestBody @Validated Boolean isLike) {
        userService.updateUserLike(userId, userPostId, isLike);
        return ResponseEntity.accepted().body(isLike);
    }

}
