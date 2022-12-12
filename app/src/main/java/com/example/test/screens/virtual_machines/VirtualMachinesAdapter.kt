package com.example.test.screens.virtual_machines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.VmItemBinding
import com.example.test.domain.VirtualMachine
import java.time.LocalDate

class VirtualMachinesAdapter(val clickListener: VirtualMachineListener) : ListAdapter<VirtualMachine, VirtualMachineViewHolder>(VirtualMachineListDiffCallback()) {

    override fun onBindViewHolder(holder: VirtualMachineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VirtualMachineViewHolder {
        return VirtualMachineViewHolder.from(parent)
    }

}

class VirtualMachineViewHolder(val binding: VmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: VirtualMachineListener, item : VirtualMachine) {


        if(item.beginDate <= LocalDate.now() && //startDate is in past
            item.endDate > LocalDate.now() && //endDate is in future
            item.beginDate < item.endDate//startdate is before enddate
        ){
            binding.vmActiveTextview.text = "actief"
        }
        else{
            binding.vmActiveTextview.text = "inactief"
        }
        binding.vmMemoryTextview.text = item.memoryAmount.toString()
        binding.vmNameTextview.text = item.name
        binding.vmVcpuTextview.text = item.vCPUAmount.toString()
        binding.vmStorageTextview.text = item.storageAmount.toString()
        binding.vmItem = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent : ViewGroup) : VirtualMachineViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = VmItemBinding.inflate(layoutInflater, parent, false)
            return VirtualMachineViewHolder(binding)
        }
    }
}

class VirtualMachineListDiffCallback: DiffUtil.ItemCallback<VirtualMachine>() {
    override fun areItemsTheSame(oldItem: VirtualMachine, newItem: VirtualMachine): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VirtualMachine, newItem: VirtualMachine): Boolean {
        // Works perfectly because it's a dataclass
        return oldItem == newItem
    }
}

class VirtualMachineListener(val clickListener: (vmId: Int) -> Unit ) {
    fun onClick(virtualMachine : VirtualMachine) = clickListener(virtualMachine.id)
}