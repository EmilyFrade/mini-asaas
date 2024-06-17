const atlasFilterForm = document.querySelector('atlas-filter-form')

const base_url = "http://localhost:8080/payment/index"

atlasFilterForm.addEventListener("atlas-apply-filter", (e) => {
    const filters = {}
    if (e.detail.filterData.status) filters.status = e.detail.filterData.status
    const urlParams = new URLSearchParams(filters).toString()
    window.location.href = `${base_url}?${urlParams}`
})

atlasFilterForm.addEventListener("atlas-clean-filter", (e) => {
    window.location.href = base_url
})