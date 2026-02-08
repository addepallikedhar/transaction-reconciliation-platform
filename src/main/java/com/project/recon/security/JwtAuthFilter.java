package com.project.recon.security;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Claims claims = jwtUtil.parse(token);
                String role = claims.get("role", String.class);

                List<GrantedAuthority> auths =
                        List.of(new SimpleGrantedAuthority(role));

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null, auths);

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception ignored) {
                // invalid token → fall through as unauthenticated
            }
        }
        chain.doFilter(request, response);
    }
}
