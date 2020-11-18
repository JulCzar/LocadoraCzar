const cpfInput = document.getElementById('cpf')

const applyCpfMask = () => {
  const { value } = document.getElementById('cpf')
  document.getElementById('cpf').value = value
    .replace(/\D/g,'')
    .replace(/(\d{3})(\d)/,'$1.$2')
    .replace(/(\d{3})(\d)/,'$1.$2')
    .replace(/(\d{3})(\d{1,2})$/,'$1-$2')
}