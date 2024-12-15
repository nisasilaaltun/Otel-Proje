function showWheelModal() {
    document.getElementById('wheelModal').style.display = 'block';
}

function closeWheelModal() {
    document.getElementById('wheelModal').style.display = 'none';
}

function spinWheel() {
    const wheel = document.getElementById('wheel');
    const result = document.getElementById('result');
    const spinButton = document.getElementById('spinButton');

    // Butonu devre dışı bırak
    spinButton.disabled = true;

    // Rastgele derece oluştur
    const sections = 8; // Çark dilim sayısı
    const degreesPerSection = 360 / sections;
    const randomDegrees = Math.floor(Math.random() * 360) + 1440; // En az 4 tam tur

    // Çarkı döndür
    wheel.style.transition = 'transform 5s cubic-bezier(0.17, 0.67, 0.12, 0.99)';
    wheel.style.transform = rotate(${randomDegrees}deg);

    // Dönüş bittiğinde
    setTimeout(() => {
        const actualRotation = randomDegrees % 360;
        const sectionIndex = Math.floor((360 - actualRotation) / degreesPerSection);
        const discount = document.querySelectorAll('.wheel-section')[sectionIndex].getAttribute('data-discount');

        result.innerHTML = Tebrikler! %${discount} indirim kazandınız!;
        spinButton.disabled = false;

        // Backend'e kazanılan indirimi gönder
        fetch('/api/discount/spin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ discountPercentage: discount })
        });
    }, 5000);
}

// Modal dışına tıklandığında kapatma
window.onclick = function (event) {
    const modal = document.getElementById('wheelModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};