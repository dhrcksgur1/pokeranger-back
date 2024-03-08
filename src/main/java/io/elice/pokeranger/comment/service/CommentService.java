package io.elice.pokeranger.comment.service;

//@Service
//public class CommentService {
//    private final CommentRepository commentRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ProductRepository productRepository) {
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//    }
//
//    public Comment createComment(Long userId, Long productId, CommentRequestDTO commentRequestDTO) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//        Comment comment = new Comment(
//                commentRequestDTO.setUser(user),
//                commentRequestDTO.getContent()),
//                commentRequestDTO.setContent(comment);
//        );
////        comment.setUser(user);
////        comment.setProduct(product);
//        return commentRepository.save(comment);
//    }
//
////    @Transactional
////    public Comment createComment(Long userId, Long productId, CommentRequestDTO commentRequestDTO) {
////        User user = userRepository.findById(commentRequestDTO.getUserId()).orElse(null);
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() -> new RuntimeException("Product not found"));
////        Comment comment = new Comment(
////                user,
////                productDto.getName(),
////                productDto.getPrice(),
////                productDto.getStock(),
////                productDto.getDescription(),
////                productDto.getImages()
////        );
////        productRepository.save(product);
////        return productMapper.productToDto(product);
////    }
////    // 기타 CRUD 메서드는 기본적인 구현을 따릅니다.
//}
