
// Adding an onclick listener to the link in UI
// DEV MEMO:
// We are doing it after DOM content is loaded as a good practice to ensure we are not slowing down
// the page rendering. In that particular situation the addition of the onclick handler shouldn't
// really impact the page performances, but rather stick with good practices.

document.addEventListener('DOMContentLoaded', (event) => {

    const credentialOkInput = document.getElementById("credentialOkInput");

    credentialOkInput.onclick = (_) => window.close();

});
