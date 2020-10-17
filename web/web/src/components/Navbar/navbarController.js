export const toggleClass = () => {
    const $hamburger = document.querySelector('.hamburger');
    $hamburger.classList.toggle('expanded');
    
    const $menu = document.querySelector('.menu');
    $menu.classList.toggle('menuToggle');

    window.addEventListener('resize', () => {
        if(window.screen.width > 768) {
            $hamburger.classList.remove('expanded');
            $menu.classList.remove('menuToggle');
        }
    });

    window.addEventListener('scroll', () => {
        $hamburger.classList.remove('expanded');
        $menu.classList.remove('menuToggle');
    });
}