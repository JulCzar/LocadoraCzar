const applyCpfMask = () => {
  const { value } = document.querySelector('.cpfInput')
  document.querySelector('.cpfInput').value = value
    .replace(/\D/g,'')
    .replace(/(\d{3})(\d)/,'$1.$2')
    .replace(/(\d{3})(\d)/,'$1.$2')
    .replace(/(\d{3})(\d{1,2})$/,'$1-$2')
}